package models

import org.joda.time.DateTime
import play.api.libs.json._
import reactivemongo.play.json._
import reactivemongo.bson.BSONObjectID
import reactivemongo.bson._
import play.api.libs.json.JodaWrites._
import play.api.libs.json.JodaReads._

case class MoveRequest(
                  gameID: String,
                  turn: Int,
                  row: String,
                  file: String,
                  toRow: String,
                  toFile: String
                )
object MoveRequest{
  implicit val fmt : Format[MoveRequest] = Json.format[MoveRequest]
  implicit object MoveRequestBSONReader extends BSONDocumentReader[MoveRequest] {
    def read(doc: BSONDocument): MoveRequest = {
      MoveRequest(
        doc.getAs[String]("gameID").get,
        doc.getAs[Int]("turn").get,
        doc.getAs[String]("row").get,
        doc.getAs[String]("file").get,
        doc.getAs[String]("toRow").get,
        doc.getAs[String]("toFile").get
        )
    }
  }

  implicit object MoveRequestBSONWriter extends BSONDocumentWriter[MoveRequest] {
    def write(moveRequest: MoveRequest): BSONDocument = {
      BSONDocument(
        "gameID" -> moveRequest.gameID,
        "turn" -> moveRequest.turn,
        "row" -> moveRequest.row,
        "file" -> moveRequest.file,
        "toRow" -> moveRequest.toRow,
        "toFile" -> moveRequest.toFile
      )
    }
  }
}