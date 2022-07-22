package models

import org.joda.time.DateTime
import play.api.libs.json._
import play.api.libs.functional.syntax._
import reactivemongo.play.json._
import reactivemongo.bson.BSONObjectID
import reactivemongo.bson._
import play.api.libs.json.JodaWrites._
import play.api.libs.json.JodaReads._

case class Color(
                  ra: Piece,
                  nb: Piece,
                  bc: Piece,
                  q: Piece,
                  k: Piece,
                  bf: Piece,
                  ng: Piece,
                  rh: Piece,
                  pa: Piece,
                  pb: Piece,
                  pc: Piece,
                  pd: Piece,
                  pe: Piece,
                  pf: Piece,
                  pg: Piece,
                  ph: Piece,
                )
object Color{
  implicit val fmt : Format[Color] = Json.format[Color]  
  implicit object ColorBSONReader extends BSONDocumentReader[Color] {
    def read(doc: BSONDocument): Color = {
      Color(
        doc.getAs[Piece]("ra").get,
        doc.getAs[Piece]("nb").get,
        doc.getAs[Piece]("bc").get,
        doc.getAs[Piece]("q").get,
        doc.getAs[Piece]("k").get,
        doc.getAs[Piece]("bf").get,
        doc.getAs[Piece]("ng").get,
        doc.getAs[Piece]("rh").get,
        doc.getAs[Piece]("pa").get,
        doc.getAs[Piece]("pb").get,
        doc.getAs[Piece]("pc").get,
        doc.getAs[Piece]("pd").get,
        doc.getAs[Piece]("pe").get,
        doc.getAs[Piece]("pf").get,
        doc.getAs[Piece]("pg").get,
        doc.getAs[Piece]("ph").get)
    }
  }

  implicit object ColorBSONWriter extends BSONDocumentWriter[Color] {
    def write(color: Color): BSONDocument = {
      BSONDocument(
        "ra" -> color.ra,
        "nb" -> color.nb,
        "bc" -> color.bc,
        "q" -> color.q,
        "k" -> color.k,
        "bf" -> color.bf,
        "ng" -> color.ng,
        "rh" -> color.rh,
        "pa" -> color.pa,
        "pb" -> color.pb,
        "pc" -> color.pc,
        "pd" -> color.pd,
        "pe" -> color.pe,
        "pf" -> color.pf,
        "pg" -> color.pg,
        "ph" -> color.ph
      )
    }
  }
}