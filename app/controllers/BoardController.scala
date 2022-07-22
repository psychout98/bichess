package controllers

import models._
import javax.inject._
import play.api.mvc._
import repositories.BoardRepository
import reactivemongo.bson.BSONObjectID
import play.api.libs.json.{Json, __}
import scala.util.{Failure, Success}
import scala.concurrent.{ExecutionContext, Future}
import service.BoardService.BoardMethods._
import play.api.libs.json.JsValue

@Singleton
class BoardController @Inject()(
                                 implicit executionContext: ExecutionContext,
                                 val boardRepository: BoardRepository,
                                 val controllerComponents: ControllerComponents)
  extends BaseController {


  // def getMoves(id:String, piece:String):Action[AnyContent] = Action.async { implicit request: Request[AnyContent] =>
  //   val objectIdTryResult = BSONObjectID.parse(id)
  //   objectIdTryResult match {
  //     case Success(objectId) => boardRepository.findOne(objectId).map {
  //       board => Ok(Json.toJson(findMoves(board.get,piece)))
  //     }
  //     case Failure(_) => Future.successful(BadRequest("Cannot parse the board id"))
  //   }
  // }
  def findGame(id: String):Action[AnyContent] = Action.async { implicit request: Request[AnyContent] =>
    boardRepository.findAllByGameId(id).map {
      boards => Ok(Json.toJson(boards))
    }
  }

  def findAll():Action[AnyContent] = Action.async { implicit request: Request[AnyContent] =>
    boardRepository.findAll().map {
      boards => Ok(Json.toJson(boards))
    }
  }

  def findOne(id:String):Action[AnyContent] = Action.async { implicit request: Request[AnyContent] =>
    val objectIdTryResult = BSONObjectID.parse(id)
    objectIdTryResult match {
      case Success(objectId) => boardRepository.findOne(objectId).map {
        board => Ok(Json.toJson(board))
      }
      case Failure(_) => Future.successful(BadRequest("Cannot parse the board id"))
    }
  }

  // def move():Action[JsValue] = Action.async(controllerComponents.parsers.json) { implicit request => {
  //   request.body.validate[MoveRequest].fold(
  //     _ => Future.successful(BadRequest("Cannot parse request body")),
  //     moveRequest =>{
  //       boardRepository.update(moveBoard(moveRequest)).map {

  //       }
  //     }
  //   )

  // }}

  def create():Action[JsValue] = Action.async(controllerComponents.parsers.json) { implicit request => {
    request.body.validate[Board].fold(
      _ => Future.successful(BadRequest("Cannot parse request body")),
      board =>
        boardRepository.create(board).map {
          writtenBoard => Created(Json.toJson(board))
        }
    )
  }}

  def update(
              id: String):Action[JsValue]  = Action.async(controllerComponents.parsers.json) { implicit request => {
    request.body.validate[Board].fold(
      _ => Future.successful(BadRequest("Cannot parse request body")),
      board =>{
        val objectIdTryResult = BSONObjectID.parse(id)
        objectIdTryResult match {
          case Success(objectId) => boardRepository.update(objectId, board).map {
            result => Ok(Json.toJson(result.ok))
          }
          case Failure(_) => Future.successful(BadRequest("Cannot parse the board id"))
        }
      }
    )
  }}

  def delete(id: String):Action[AnyContent]  = Action.async { implicit request => {
    val objectIdTryResult = BSONObjectID.parse(id)
    objectIdTryResult match {
      case Success(objectId) => boardRepository.delete(objectId).map {
        _ => NoContent
      }
      case Failure(_) => Future.successful(BadRequest("Cannot parse the board id"))
    }
  }}
}