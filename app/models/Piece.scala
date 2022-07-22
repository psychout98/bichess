package models

import org.joda.time.DateTime
import play.api.libs.json._
import play.api.libs.functional.syntax._
import reactivemongo.play.json._
import reactivemongo.bson.BSONObjectID
import reactivemongo.bson._
import play.api.libs.json.JodaWrites._
import play.api.libs.json.JodaReads._

case class Piece(
                  name: String,
                  species: Int, // 0: pawn, 1: rook, 2: knight, 3: bishop, 4: king, 5: queen
                  row: Int,
                  file: Int
                )
object Piece{
  implicit val fmt : Format[Piece] = Json.format[Piece]  
  implicit object PieceBSONReader extends BSONDocumentReader[Piece] {
    def read(doc: BSONDocument): Piece = {
      Piece(
        doc.getAs[String]("name").get,
        doc.getAs[Int]("species").get,
        doc.getAs[Int]("row").get,
        doc.getAs[Int]("file").get)
    }
  }

  implicit object PieceBSONWriter extends BSONDocumentWriter[Piece] {
    def write(piece: Piece): BSONDocument = {
      BSONDocument(
        "name" -> piece.name,
        "species" -> piece.species,
        "row" -> piece.row,
        "file" -> piece.file
      )
    }
  }
}