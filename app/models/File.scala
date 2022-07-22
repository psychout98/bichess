package models

import org.joda.time.DateTime
import play.api.libs.json._
import play.api.libs.functional.syntax._
import reactivemongo.play.json._
import reactivemongo.bson.BSONObjectID
import reactivemongo.bson._
import play.api.libs.json.JodaWrites._
import play.api.libs.json.JodaReads._

case class File(
                  _1: (Int,Int),
                  _2: (Int,Int),
                  _3: (Int,Int),
                  _4: (Int,Int),
                  _5: (Int,Int),
                  _6: (Int,Int),
                  _7: (Int,Int),
                  _8: (Int,Int)
                )
object File{
  implicit val fmt : Format[File] = Json.format[File]  
  implicit object FileBSONReader extends BSONDocumentReader[File] {
    def read(doc: BSONDocument): File = {
      File(
        (doc.getAs[BSONArray]("_1").get.get(0).get.asInstanceOf[BSONInteger].value,
        doc.getAs[BSONArray]("_1").get.get(1).get.asInstanceOf[BSONInteger].value),
        (doc.getAs[BSONArray]("_2").get.get(0).get.asInstanceOf[BSONInteger].value,
        doc.getAs[BSONArray]("_2").get.get(1).get.asInstanceOf[BSONInteger].value),
        (doc.getAs[BSONArray]("_3").get.get(0).get.asInstanceOf[BSONInteger].value,
        doc.getAs[BSONArray]("_3").get.get(1).get.asInstanceOf[BSONInteger].value),
        (doc.getAs[BSONArray]("_4").get.get(0).get.asInstanceOf[BSONInteger].value,
        doc.getAs[BSONArray]("_4").get.get(1).get.asInstanceOf[BSONInteger].value),
        (doc.getAs[BSONArray]("_5").get.get(0).get.asInstanceOf[BSONInteger].value,
        doc.getAs[BSONArray]("_5").get.get(1).get.asInstanceOf[BSONInteger].value),
        (doc.getAs[BSONArray]("_6").get.get(0).get.asInstanceOf[BSONInteger].value,
        doc.getAs[BSONArray]("_6").get.get(1).get.asInstanceOf[BSONInteger].value),
        (doc.getAs[BSONArray]("_7").get.get(0).get.asInstanceOf[BSONInteger].value,
        doc.getAs[BSONArray]("_7").get.get(1).get.asInstanceOf[BSONInteger].value),
        (doc.getAs[BSONArray]("_8").get.get(0).get.asInstanceOf[BSONInteger].value,
        doc.getAs[BSONArray]("_8").get.get(1).get.asInstanceOf[BSONInteger].value)
      )
    }
  }

  implicit object FileBSONWriter extends BSONDocumentWriter[File] {
    def write(file: File): BSONDocument = {
      BSONDocument(
        "_1" -> BSONArray.apply(file._1._1,file._1._2),
        "_2" -> BSONArray.apply(file._2._1,file._2._2),
        "_3" -> BSONArray.apply(file._3._1,file._3._2),
        "_4" -> BSONArray.apply(file._4._1,file._4._2),
        "_5" -> BSONArray.apply(file._5._1,file._5._2),
        "_6" -> BSONArray.apply(file._6._1,file._6._2),
        "_7" -> BSONArray.apply(file._7._1,file._7._2),
        "_8" -> BSONArray.apply(file._8._1,file._8._2)
      )
    }
  }
}