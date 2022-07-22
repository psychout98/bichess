package models

import org.joda.time.DateTime
import play.api.libs.json._
import reactivemongo.play.json._
import reactivemongo.bson.BSONObjectID
import reactivemongo.bson._
import play.api.libs.json.JodaWrites._
import play.api.libs.json.JodaReads._

case class BoardRequest(
                  white: String,
                  black: String,
                  gameID: String,
                  turn: Int
                )
object BoardRequest{
  implicit val fmt : Format[BoardRequest] = Json.format[BoardRequest]
  implicit object BoardRequestBSONReader extends BSONDocumentReader[BoardRequest] {
    def read(doc: BSONDocument): BoardRequest = {
      BoardRequest(
        doc.getAs[String]("white").get,
        doc.getAs[String]("black").get,
        doc.getAs[String]("gameID").get,
        doc.getAs[Int]("turn").get
        )
    }
  }

  implicit object BoardRequestBSONWriter extends BSONDocumentWriter[BoardRequest] {
    def write(boardRequest: BoardRequest): BSONDocument = {
      BSONDocument(
        "white" -> boardRequest.white,
        "black" -> boardRequest.black,
        "gameID" -> boardRequest.gameID,
        "turn" -> boardRequest.turn
      )
    }
  }
}