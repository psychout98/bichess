package repositories

import models._
import service._
import javax.inject._
import reactivemongo.api.bson.collection.BSONCollection
import play.modules.reactivemongo.ReactiveMongoApi
import scala.concurrent.{ExecutionContext, Future}
import reactivemongo.api.{Cursor, ReadPreference}
import reactivemongo.bson.{BSONDocument, BSONObjectID}
import org.joda.time.DateTime
import reactivemongo.api.commands.WriteResult


@Singleton
class BoardRepository @Inject()(
                                 implicit executionContext: ExecutionContext,
                                 reactiveMongoApi: ReactiveMongoApi
                               ) {
  def collection: Future[BSONCollection] = reactiveMongoApi.database.map(db => db.collection("boards"))

  def findAll(limit: Int = 100): Future[Seq[Board]] = {

    collection.flatMap(
      _.find(BSONDocument(), Option.empty[Board])
        .cursor[Board](ReadPreference.Primary)
        .collect[Seq](limit, Cursor.FailOnError[Seq[Board]]())
    )
  }

  def findAllByGameId(id: String, limit: Int = 150): Future[Seq[Board]] = {
    collection.flatMap(
      _.find(BSONDocument("gameID" -> id), Option.empty[Board])
        .cursor[Board](ReadPreference.Primary)
        .collect[Seq](limit, Cursor.FailOnError[Seq[Board]]())
    )
  }

  def findOne(id: BSONObjectID): Future[Option[Board]] = {
    collection.flatMap(_.find(BSONDocument("_id" -> id), Option.empty[Board]).one[Board])
  }

  def create(board: Board): Future[WriteResult] = {
    //val board = defaultBoard(boardRequest);
    collection.flatMap(_.insert(ordered = false)
      .one(board.copy(_creationDate = Some(new DateTime()), _updateDate = Some(new DateTime()))))
  }

  def update(id: BSONObjectID, board: Board):Future[WriteResult] = {
    collection.flatMap(
      _.update(ordered = false).one(BSONDocument("_id" -> id),
        board.copy(
          _updateDate = Some(new DateTime())))
    )
  }

  def delete(id: BSONObjectID):Future[WriteResult] = {
    collection.flatMap(
      _.delete().one(BSONDocument("_id" -> id), Some(1))
    )
  }

//   def move(moveRequest: MoveRequest): Future[WriteResult] = {
//     collection.flatMap(_.insert(ordered = false)
//       .one(board.copy(_creationDate = Some(new DateTime()), _updateDate = Some(new DateTime()))))
//     }
    //collection.flatMap(_.find(BSONDocument("gameID" -> moveRequest.gameID,"turn" -> moveRequest.turn), Option.empty[Board]).one[Board])
    //val boardPrev: Future[Option[Board]] = collection.flatMap(_.find(BSONDocument("gameID" -> moveRequest.gameID,"turn" -> moveRequest.turn), Option.empty[Board]).one[Board])
    // boardPrev.andThen(boardFound => collection.flatMap(_.insert(ordered = false)
    //   .one(moveBoard(boardFound.get.get, moveRequest).copy(_creationDate = Some(new DateTime()), _updateDate = Some(new DateTime())))))
    
//   }



}