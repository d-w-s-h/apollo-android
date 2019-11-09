package com.apollographql.apollo.gradle.test

import com.apollographql.apollo.gradle.util.TestUtils
import org.gradle.testkit.runner.TaskOutcome
import org.junit.Assert
import org.junit.Test


class LazyTests {
  @Test
  fun `properties are not called during configuration`() {

    val apolloConfiguration = """
configure<ApolloExtension> {
  useSemanticNaming.set(project.provider {
      throw IllegalArgumentException("this should not be called during configuration")
  })
}
    """.trimIndent()
    TestUtils.withProject(
        usesKotlinDsl = true,
        plugins = listOf(TestUtils.javaPlugin, TestUtils.apolloPlugin),
        apolloConfiguration = apolloConfiguration
    ) { dir ->
      TestUtils.executeGradle(dir, "tasks", "--all")
    }
  }

  @Test
  fun `configuring compilation unit input carries task dependencies`() {

    val apolloConfiguration = """
abstract class InstallGraphQLFilesTask: DefaultTask() {
  @get:OutputDirectory
  abstract val outputDir: DirectoryProperty

  @TaskAction
  fun taskAction() {
    println("installing graphql files")
    project.file( "src/main/graphql/com/example").copyRecursively(outputDir.asFile.get())
  }
}

val installTask = tasks.register("installTask", InstallGraphQLFilesTask::class.java) {
  outputDir.set(project.file("build/toto"))
}

configure<ApolloExtension> {
  onCompilationUnits {
    graphqlSourceDirectorySet.srcDir(installTask.flatMap { it.outputDir })
  }
}
""".trimIndent()

    TestUtils.withProject(
        usesKotlinDsl = true,
        plugins = listOf(TestUtils.javaPlugin, TestUtils.apolloPlugin),
        apolloConfiguration = apolloConfiguration
    ) { dir ->
      val result = TestUtils.executeTask("generateApolloSources", dir)
      Assert.assertEquals(TaskOutcome.SUCCESS, result.task(":generateApolloSources")!!.outcome)
      Assert.assertEquals(TaskOutcome.SUCCESS, result.task(":installTask")?.outcome)
    }
  }
}
