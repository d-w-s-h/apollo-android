// AUTO-GENERATED FILE. DO NOT MODIFY.
//
// This class was automatically generated by Apollo GraphQL plugin from the GraphQL queries it found.
// It should not be modified by hand.
//
package com.example.named_fragment_delegate

import com.apollographql.apollo.api.Operation
import com.apollographql.apollo.api.OperationName
import com.apollographql.apollo.api.Query
import com.apollographql.apollo.api.Response
import com.apollographql.apollo.api.ScalarTypeAdapters
import com.apollographql.apollo.api.ScalarTypeAdapters.Companion.DEFAULT
import com.apollographql.apollo.api.internal.OperationRequestBodyComposer
import com.apollographql.apollo.api.internal.QueryDocumentMinifier
import com.apollographql.apollo.api.internal.ResponseFieldMapper
import com.apollographql.apollo.api.internal.ResponseFieldMarshaller
import com.apollographql.apollo.api.internal.SimpleOperationResponseParser
import com.apollographql.apollo.api.internal.Throws
import com.example.named_fragment_delegate.adapter.TestQuery_ResponseAdapter
import com.example.named_fragment_delegate.fragment.DroidDetail
import com.example.named_fragment_delegate.fragment.HumanDetail
import kotlin.Any
import kotlin.Boolean
import kotlin.String
import kotlin.Suppress
import kotlin.collections.List
import okio.Buffer
import okio.BufferedSource
import okio.ByteString
import okio.IOException

@Suppress("NAME_SHADOWING", "UNUSED_ANONYMOUS_PARAMETER", "LocalVariableName",
    "RemoveExplicitTypeArguments", "NestedLambdaShadowedImplicitParameter", "PropertyName",
    "RemoveRedundantQualifierName")
class TestQuery : Query<TestQuery.Data, Operation.Variables> {
  override fun operationId(): String = OPERATION_ID

  override fun queryDocument(): String = QUERY_DOCUMENT

  override fun variables(): Operation.Variables = Operation.EMPTY_VARIABLES

  override fun name(): OperationName = OPERATION_NAME

  override fun responseFieldMapper(): ResponseFieldMapper<Data> {
    return ResponseFieldMapper { reader ->
      TestQuery_ResponseAdapter.fromResponse(reader)
    }
  }

  @Throws(IOException::class)
  override fun parse(source: BufferedSource, scalarTypeAdapters: ScalarTypeAdapters):
      Response<Data> {
    return SimpleOperationResponseParser.parse(source, this, scalarTypeAdapters)
  }

  @Throws(IOException::class)
  override fun parse(byteString: ByteString, scalarTypeAdapters: ScalarTypeAdapters):
      Response<Data> {
    return parse(Buffer().write(byteString), scalarTypeAdapters)
  }

  @Throws(IOException::class)
  override fun parse(source: BufferedSource): Response<Data> {
    return parse(source, DEFAULT)
  }

  @Throws(IOException::class)
  override fun parse(byteString: ByteString): Response<Data> {
    return parse(byteString, DEFAULT)
  }

  override fun composeRequestBody(scalarTypeAdapters: ScalarTypeAdapters): ByteString {
    return OperationRequestBodyComposer.compose(
      operation = this,
      autoPersistQueries = false,
      withQueryDocument = true,
      scalarTypeAdapters = scalarTypeAdapters
    )
  }

  override fun composeRequestBody(): ByteString = OperationRequestBodyComposer.compose(
    operation = this,
    autoPersistQueries = false,
    withQueryDocument = true,
    scalarTypeAdapters = DEFAULT
  )

  override fun composeRequestBody(
    autoPersistQueries: Boolean,
    withQueryDocument: Boolean,
    scalarTypeAdapters: ScalarTypeAdapters
  ): ByteString = OperationRequestBodyComposer.compose(
    operation = this,
    autoPersistQueries = autoPersistQueries,
    withQueryDocument = withQueryDocument,
    scalarTypeAdapters = scalarTypeAdapters
  )

  /**
   * The query type, represents all of the entry points into our object graph
   */
  data class Data(
    val hero: Hero?
  ) : Operation.Data {
    override fun marshaller(): ResponseFieldMarshaller {
      return ResponseFieldMarshaller { writer ->
        TestQuery_ResponseAdapter.Data.toResponse(writer, this)
      }
    }

    /**
     * A character from the Star Wars universe
     */
    interface Hero {
      val __typename: String

      fun marshaller(): ResponseFieldMarshaller

      interface Droid : Hero, DroidDetail {
        override val __typename: String

        /**
         * What others call this droid
         */
        override val name: String

        /**
         * This droid's primary function
         */
        override val primaryFunction: String?

        /**
         * This droid's friends, or an empty list if they have none
         */
        override val friends: List<Friend?>?

        override fun marshaller(): ResponseFieldMarshaller

        /**
         * A character from the Star Wars universe
         */
        interface Friend : DroidDetail.Friend {
          /**
           * The name of the character
           */
          override val name: String

          override fun marshaller(): ResponseFieldMarshaller
        }
      }

      interface Human : Hero, HumanDetail {
        override val __typename: String

        /**
         * What this human calls themselves
         */
        override val name: String

        /**
         * Profile link
         */
        override val profileLink: Any

        /**
         * The friends of the human exposed as a connection with edges
         */
        override val friendsConnection: FriendsConnection

        override fun marshaller(): ResponseFieldMarshaller

        /**
         * A connection object for a character's friends
         */
        interface FriendsConnection : HumanDetail.FriendsConnection {
          /**
           * The edges for each of the character's friends.
           */
          override val edges: List<Edge?>?

          override fun marshaller(): ResponseFieldMarshaller

          /**
           * An edge object for a character's friends
           */
          interface Edge : HumanDetail.FriendsConnection.Edge {
            /**
             * The character represented by this friendship edge
             */
            override val node: Node?

            override fun marshaller(): ResponseFieldMarshaller

            /**
             * A character from the Star Wars universe
             */
            interface Node : HumanDetail.FriendsConnection.Edge.Node {
              /**
               * The name of the character
               */
              override val name: String

              override fun marshaller(): ResponseFieldMarshaller
            }
          }
        }
      }

      data class DroidHero(
        override val __typename: String,
        /**
         * What others call this droid
         */
        override val name: String,
        /**
         * This droid's primary function
         */
        override val primaryFunction: String?,
        /**
         * This droid's friends, or an empty list if they have none
         */
        override val friends: List<Friend?>?
      ) : Hero, Droid, DroidDetail {
        override fun marshaller(): ResponseFieldMarshaller {
          return ResponseFieldMarshaller { writer ->
            TestQuery_ResponseAdapter.Data.Hero.DroidHero.toResponse(writer, this)
          }
        }

        /**
         * A character from the Star Wars universe
         */
        data class Friend(
          /**
           * The name of the character
           */
          override val name: String
        ) : Droid.Friend, DroidDetail.Friend {
          override fun marshaller(): ResponseFieldMarshaller {
            return ResponseFieldMarshaller { writer ->
              TestQuery_ResponseAdapter.Data.Hero.DroidHero.Friend.toResponse(writer, this)
            }
          }
        }
      }

      data class HumanHero(
        override val __typename: String,
        /**
         * What this human calls themselves
         */
        override val name: String,
        /**
         * Profile link
         */
        override val profileLink: Any,
        /**
         * The friends of the human exposed as a connection with edges
         */
        override val friendsConnection: FriendsConnection
      ) : Hero, Human, HumanDetail {
        override fun marshaller(): ResponseFieldMarshaller {
          return ResponseFieldMarshaller { writer ->
            TestQuery_ResponseAdapter.Data.Hero.HumanHero.toResponse(writer, this)
          }
        }

        /**
         * A connection object for a character's friends
         */
        data class FriendsConnection(
          /**
           * The edges for each of the character's friends.
           */
          override val edges: List<Edge?>?
        ) : Human.FriendsConnection, HumanDetail.FriendsConnection {
          override fun marshaller(): ResponseFieldMarshaller {
            return ResponseFieldMarshaller { writer ->
              TestQuery_ResponseAdapter.Data.Hero.HumanHero.FriendsConnection.toResponse(writer, this)
            }
          }

          /**
           * An edge object for a character's friends
           */
          data class Edge(
            /**
             * The character represented by this friendship edge
             */
            override val node: Node?
          ) : Human.FriendsConnection.Edge, HumanDetail.FriendsConnection.Edge {
            override fun marshaller(): ResponseFieldMarshaller {
              return ResponseFieldMarshaller { writer ->
                TestQuery_ResponseAdapter.Data.Hero.HumanHero.FriendsConnection.Edge.toResponse(writer, this)
              }
            }

            /**
             * A character from the Star Wars universe
             */
            data class Node(
              /**
               * The name of the character
               */
              override val name: String
            ) : Human.FriendsConnection.Edge.Node, HumanDetail.FriendsConnection.Edge.Node {
              override fun marshaller(): ResponseFieldMarshaller {
                return ResponseFieldMarshaller { writer ->
                  TestQuery_ResponseAdapter.Data.Hero.HumanHero.FriendsConnection.Edge.Node.toResponse(writer, this)
                }
              }
            }
          }
        }
      }

      data class OtherHero(
        override val __typename: String
      ) : Hero {
        override fun marshaller(): ResponseFieldMarshaller {
          return ResponseFieldMarshaller { writer ->
            TestQuery_ResponseAdapter.Data.Hero.OtherHero.toResponse(writer, this)
          }
        }
      }

      companion object {
        fun Hero.asDroid(): Droid? = this as? Droid

        fun Hero.droidDetails(): DroidDetail? = this as? DroidDetail

        fun Hero.asHuman(): Human? = this as? Human

        fun Hero.humanDetails(): HumanDetail? = this as? HumanDetail
      }
    }
  }

  companion object {
    const val OPERATION_ID: String =
        "727bf302bb93ddf9e3e12bad4506044461b67069ac0121c4f54417b9a98e42d4"

    val QUERY_DOCUMENT: String = QueryDocumentMinifier.minify(
          """
          |query TestQuery {
          |  hero {
          |    __typename
          |    ...DroidDetails
          |    ...HumanDetails
          |  }
          |}
          |fragment DroidDetails on Droid {
          |  __typename
          |  name
          |  primaryFunction
          |  friends {
          |    name
          |  }
          |}
          |fragment HumanDetails on Human {
          |  __typename
          |  name
          |  profileLink
          |  friendsConnection {
          |    edges {
          |      node {
          |        name
          |      }
          |    }
          |  }
          |}
          """.trimMargin()
        )

    val OPERATION_NAME: OperationName = object : OperationName {
      override fun name(): String {
        return "TestQuery"
      }
    }
  }
}