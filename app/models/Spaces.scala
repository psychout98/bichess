package models

import org.joda.time.DateTime
import play.api.libs.json._
import play.api.libs.functional.syntax._
import reactivemongo.play.json._
import reactivemongo.bson.BSONObjectID
import reactivemongo.bson._
import play.api.libs.json.JodaWrites._
import play.api.libs.json.JodaReads._

case class Spaces(
                  a: File,
                  b: File,
                  c: File,
                  d: File,
                  e: File,
                  f: File,
                  g: File,
                  h: File
                )
object Spaces{
  implicit val fmt : Format[Spaces] = Json.format[Spaces]  
  implicit object SpacesBSONReader extends BSONDocumentReader[Spaces] {
    def read(doc: BSONDocument): Spaces = {
      Spaces(
        doc.getAs[File]("a").get,
        doc.getAs[File]("b").get,
        doc.getAs[File]("c").get,
        doc.getAs[File]("d").get,
        doc.getAs[File]("e").get,
        doc.getAs[File]("f").get,
        doc.getAs[File]("g").get,
        doc.getAs[File]("h").get
      )
    }
  }

  implicit object SpacesBSONWriter extends BSONDocumentWriter[Spaces] {
    def write(spaces: Spaces): BSONDocument = {
      BSONDocument(
        "a" -> spaces.a,
        "b" -> spaces.b,
        "c" -> spaces.c,
        "d" -> spaces.d,
        "e" -> spaces.e,
        "f" -> spaces.f,
        "g" -> spaces.g,
        "h" -> spaces.h
      )
    }
  }
}