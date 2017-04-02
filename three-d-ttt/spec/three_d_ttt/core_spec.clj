(ns three-d-ttt.core-spec
  (:require [speclj.core :refer :all]
            [three-d-ttt.core :refer :all]))

(describe "a test"
  (it "FIXME, I fail."
    (should= 0 0)))



(describe "the board"
	(it "should be vec 1-9"
		(should= board [0 1 2 3 4 5 6 7 8 9 10 11 12 13 14 15 16 17 18 19 20 21 22 23 24 25 26])))


(describe "valid-move?"
	(it "should be true open inbound space"
		(should= true (valid-move? board 2)))

	(it "catched out of bounds"
		(should= false (valid-move? board 88)))

	(it "returns false for bad input"
		(should= false (valid-move? board "a")))

	(it "returns false if place is taken"
		(should-be-nil (valid-move? ["x" 1 2 3 4 5 6 7 8 9 10 11 12 13 14 15 16 17 18 19 20 21 22 23 24 25 26] 0))))



(describe "place-piece"
	(it "should place piece"
		(should= ["x" 1 2 3 4 5 6 7 8 9 10 11 12 13 14 15 16 17 18 19 20 21 22 23 24 25 26] (place-piece board "x" 0))))



(describe "selects opposing piece"	
	(it "should return opposing piece"
		(should= "x" (opposing-piece "0"))))



(describe "return new board for turn")


(describe "should be winnfin"
	(it "should be 1"
		(should= 1 (winning-board? ["x" "x" "x" 3 4 5 6 7 8]))))


(describe "checks all slices for win"
	(it "should return 2 if no win"
		(should= 2 (check-all-slices-for-win? board)))

	(it "should return 1 if win on diagonal slice 0 13 26"
		(should= 1 (check-all-slices-for-win? ["x" 1 2 3 4 5 6 7 8 9 10 11 12 "x" 14 15 16 17 18 19 20 21 22 23 24 25 "x"])))

	(it "should return 1 if win on diagonal slice 18 22 26"
		(should= 1 (check-all-slices-for-win? [0 1 2 3 4 5 6 7 8 9 10 11 12 13 14 15 16 17 "x" 19 20 21 "x" 23 24 25 "x"])))

	(it "should return 2 if win for no wins"
		(should= 2 (check-all-slices-for-win? ["x" "o" "x" "o" "x" "o" "o" "x" "o" "x" "o" "x" "o" "x" "o" "o" "x" "o" 18 19 20 21 22 23 24 25 26]))))


(describe "get best score 3d"
	(it "should pick immediate winning move"
		(should= 26 (get-best-score ["x" "o" "x" "o" "x" "o" "o" "x" "o" "x" "o" "x" "o" "x" "o" "o" "x" "o" 18 19 20 21 22 23 24 25 26] "x"
			))))



; (describe "game-over"
; 	(it "should not be a game over"
; 		(should= false (game-over? board))))

; (comment
;  18 19 20
;  21 22 23 
;  24 25 26

;  9x 10o 11x
;  12o 13x 14o 
;  15o 16x 17o 

;  0x 1o 2x 
;  3o 4x 5o 
;  6o 7x 8o 


; x o x
; o x o 
; o x o



; )









;