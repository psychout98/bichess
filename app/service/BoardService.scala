package service

import models._
import javax.inject._
import reactivemongo.api.bson.collection.BSONCollection
import play.modules.reactivemongo.ReactiveMongoApi
import scala.concurrent.{ExecutionContext, Future}
import reactivemongo.api.{Cursor, ReadPreference}
import reactivemongo.bson.{BSONDocument, BSONObjectID}
import org.joda.time.DateTime
import reactivemongo.api.commands.WriteResult

case class BoardService()

object BoardService {

    val pawnMoves: Vector[(Int,Int)] = Vector[(Int,Int)]((1,0),(2,0),(1,1),(1,-1))

    object BoardMethods {
    def findMoves(board: Board,piece: String): Seq[(String,(Int,Int))] = {
        return Seq[(String,(Int,Int))]((piece,(1,0)))
    }


    def moveBoard(board: Board, moveRequest: MoveRequest): Board = {
        return board;
    }

    }
    }


//       def defaultBoard(boardRequest: BoardRequest):Board = {
//     return Board(_id = Some(BSONObjectID.generate()), gameID = boardRequest.gameID, turn = boardRequest.turn,
//     _creationDate = Some(new DateTime()), _updateDate = Some(new DateTime()),
//     // white =  
//     // Color(
//     //     ra = 
//     //         Piece(
//     //             name = "white-rook-a",
//     //             species = 1,
//     //             row = 0,
//     //             file = 0
//     //         ),
//     //     nb =
//     //         Piece(
//     //             name = "white-night-b",
//     //             species  = 2,
//     //             row = 0,
//     //             file = 1
//     //         ),
//     //     bc =
//     //         Piece(
//     //             name = "white-bishop-c",
//     //             species  = 3,
//     //             row = 0,
//     //             file = 2
//     //         ),
//     //     q  =
//     //         Piece(
//     //             name = "white-queen-d",
//     //             species  = 4,
//     //             row = 0,
//     //             file = 3
//     //         ),
//     //     k  =
//     //         Piece(
//     //             name = "white-king-e",
//     //             species  = 5,
//     //             row = 0,
//     //             file = 4
//     //         ),
//     //     bf  =
//     //         Piece(
//     //             name = "white-bishop-f",
//     //             species  = 3,
//     //             row = 0,
//     //             file = 5
//     //         ),
//     //     ng  =
//     //         Piece(
//     //             name = "white-night-g",
//     //             species  = 2,
//     //             row = 0,
//     //             file = 6
//     //         ),
//     //     rh  =
//     //         Piece(
//     //             name = "white-rook-h",
//     //             species  = 1,
//     //             row = 0,
//     //             file = 7
//     //         ),
//     //     pa  =
//     //         Piece(
//     //             name = "white-pawn-a",
//     //             species  = 0,
//     //             row = 1,
//     //             file = 0
//     //         ),
//     //     pb  =
//     //         Piece(
//     //             name = "white-pawn-b",
//     //             species  = 0,
//     //             row = 1,
//     //             file = 1
//     //         ),
//     //     pc  =
//     //         Piece(
//     //             name = "white-pawn-c",
//     //             species  = 0,
//     //             row = 1,
//     //             file = 2
//     //         ),
//     //     pd  =
//     //         Piece(
//     //             name = "white-pawn-d",
//     //             species  = 0,
//     //             row = 1,
//     //             file = 3
//     //         ),
//     //     pe  =
//     //         Piece(
//     //             name = "white-pawn-e",
//     //             species  = 0,
//     //             row = 1,
//     //             file = 4
//     //         ),
//     //     pf  =
//     //         Piece(
//     //             name = "white-pawn-f",
//     //             species  = 0,
//     //             row = 1,
//     //             file = 5
//     //         ),
//     //     pg  =
//     //         Piece(
//     //             name = "white-pawn-g",
//     //             species  = 0,
//     //             row = 1,
//     //             file = 6
//     //         ),
//     //     ph  =
//     //         Piece(
//     //             name = "white-pawn-h",
//     //             species  = 0,
//     //             row = 1,
//     //             file = 7
//     //         )
//     // ) , black = 
//     //   Color(
//     //     ra = 
//     //         Piece(
//     //             name = "black-rook-a",
//     //             species = 1,
//     //             row = 7,
//     //             file = 0
//     //         ),
//     //     nb =
//     //         Piece(
//     //             name = "black-night-b",
//     //             species  = 2,
//     //             row = 7,
//     //             file = 1
//     //         ),
//     //     bc =
//     //         Piece(
//     //             name = "black-bishop-c",
//     //             species  = 3,
//     //             row = 7,
//     //             file = 2
//     //         ),
//     //     q  =
//     //         Piece(
//     //             name = "black-queen-d",
//     //             species  = 4,
//     //             row = 7,
//     //             file = 3
//     //         ),
//     //     k  =
//     //         Piece(
//     //             name = "black-king-e",
//     //             species  = 5,
//     //             row = 7,
//     //             file = 4
//     //         ),
//     //     bf  =
//     //         Piece(
//     //             name = "black-bishop-f",
//     //             species  = 3,
//     //             row = 7,
//     //             file = 5
//     //         ),
//     //     ng  =
//     //         Piece(
//     //             name = "black-night-g",
//     //             species  = 2,
//     //             row = 7,
//     //             file = 6
//     //         ),
//     //     rh  =
//     //         Piece(
//     //             name = "black-rook-h",
//     //             species  = 1,
//     //             row = 7,
//     //             file = 7
//     //         ),
//     //     pa  =
//     //         Piece(
//     //             name = "black-pawn-a",
//     //             species  = 0,
//     //             row = 6,
//     //             file = 0
//     //         ),
//     //     pb  =
//     //         Piece(
//     //             name = "black-pawn-b",
//     //             species  = 0,
//     //             row = 6,
//     //             file = 1
//     //         ),
//     //     pc  =
//     //         Piece(
//     //             name = "black-pawn-c",
//     //             species  = 0,
//     //             row = 6,
//     //             file = 2
//     //         ),
//     //     pd  =
//     //         Piece(
//     //             name = "black-pawn-d",
//     //             species  = 0,
//     //             row = 6,
//     //             file = 3
//     //         ),
//     //     pe  =
//     //         Piece(
//     //             name = "black-pawn-e",
//     //             species  = 0,
//     //             row = 6,
//     //             file = 4
//     //         ),
//     //     pf  =
//     //         Piece(
//     //             name = "black-pawn-f",
//     //             species  = 0,
//     //             row = 6,
//     //             file = 5
//     //         ),
//     //     pg  =
//     //         Piece(
//     //             name = "black-pawn-g",
//     //             species  = 0,
//     //             row = 6,
//     //             file = 6
//     //         ),
//     //     ph  =
//     //         Piece(
//     //             name = "black-pawn-h",
//     //             species  = 0,
//     //             row = 6,
//     //             file = 7
//     //         )
//     // ), 
//     players =  (boardRequest.white, boardRequest.black),
//     spaces = Spaces(
      // "a" : {"_1" : [1,3], "_2" : [1,4], "_3" : [1,5], "_4" : [1,6], "_5" : [1,7], "_6" : [1,5], "_7" : [1,4], "_8" : [1,3]},
      // "b" : {"_1" : [1,1], "_2" : [1,1], "_3" : [1,1], "_4" : [1,1], "_5" : [1,1], "_6" : [1,1], "_7" : [1,1], "_8" : [1,1]},
      // "c" : {"_1" : [0,0], "_2" : [0,0], "_3" : [0,0], "_4" : [0,0], "_5" : [0,0], "_6" : [0,0], "_7" : [0,0], "_8" : [0,0]},
      // "d" : {"_1" : [0,0], "_2" : [0,0], "_3" : [0,0], "_4" : [0,0], "_5" : [0,0], "_6" : [0,0], "_7" : [0,0], "_8" : [0,0]},
      // "e" : {"_1" : [0,0], "_2" : [0,0], "_3" : [0,0], "_4" : [0,0], "_5" : [0,0], "_6" : [0,0], "_7" : [0,0], "_8" : [0,0]},
      // "f" : {"_1" : [0,0], "_2" : [0,0], "_3" : [0,0], "_4" : [0,0], "_5" : [0,0], "_6" : [0,0], "_7" : [0,0], "_8" : [0,0]},
      // "g" : {"_1" : [2,2], "_2" : [2,2], "_3" : [2,2], "_4" : [2,2], "_5" : [2,2], "_6" : [2,2], "_7" : [2,2], "_8" : [2,2]},
      // "h" : {"_1" : [2,3], "_2" : [2,4], "_3" : [2,5], "_4" : [2,6], "_5" : [2,7], "_6" : [2,5], "_7" : [2,4], "_8" : [2,3]}
//     ))
//   }
// /*
// empty: (0,0)
// white: 1
// black: 2

// white pawn: 1
// black pawn: 2
// rook: 3
// knight: 4
// bishop: 5
// queen: 6
// king: 7
// */
// }