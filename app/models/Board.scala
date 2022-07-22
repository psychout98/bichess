package models

import org.joda.time.DateTime
import play.api.libs.json._
import reactivemongo.play.json._
import reactivemongo.bson.BSONObjectID
import reactivemongo.bson._
import play.api.libs.json.JodaWrites._
import play.api.libs.json.JodaReads._
import models.Color
import java.util.LinkedList

case class Board(
                  _id: Option[BSONObjectID],
                  gameID: String,
                  turn: Int,
                  _creationDate: Option[DateTime],
                  _updateDate: Option[DateTime],
                  players: (String, String),
                  spaces: Spaces
                )
object Board{
  implicit val fmt : Format[Board] = Json.format[Board]
  implicit object BoardBSONReader extends BSONDocumentReader[Board] {
    def read(doc: BSONDocument): Board = {
      Board(
        doc.getAs[BSONObjectID]("_id"),
        doc.getAs[String]("gameID").get,
        doc.getAs[Int]("turn").get,
        doc.getAs[BSONDateTime]("_creationDate").map(dt => new DateTime(dt.value)),
        doc.getAs[BSONDateTime]("_updateDate").map(dt => new DateTime(dt.value)),
        (doc.getAs[BSONArray]("players").get.get(0).get.asInstanceOf[BSONString].value,
        doc.getAs[BSONArray]("players").get.get(1).get.asInstanceOf[BSONString].value),
        doc.getAs[Spaces]("spaces").get
      )
    }
  }

  implicit object BoardBSONWriter extends BSONDocumentWriter[Board] {
    def write(board: Board): BSONDocument = {
      BSONDocument(
        "_id" -> board._id,
        "gameID" -> board.gameID,
        "turn" -> board.turn,
        "_creationDate" -> board._creationDate.map(date => BSONDateTime(date.getMillis)),
        "_updateDate" -> board._updateDate.map(date => BSONDateTime(date.getMillis)),
        "players" -> BSONArray.apply(board.players._1,board.players._2),
        "spaces" -> board.spaces
      )
    }
  }
}