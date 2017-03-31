(ns three-d-ttt.core)


; (def board [0 1 2 3 4 5 6 7 8 9 10 11 12 13 14 15 16 17 18 19 20 21 22 23 24 25 26])

 (def board ["x" 1 2 3 4 5 6 7 8 9 10 11 12 "x" 14 15 16 17 18 19 20 21 22 23 24 25 26])

(defn print-board [board]
	(println)
 	(println (str (board 0) " | " (board 1) " | " (board 2) ) )
  	(println "-------------")
  	(println (str (board 3) " | " (board 4) " | " (board 5) ) )
  	(println "-------------")
  	(println (str (board 6) " | " (board 7) " | " (board 8) ) )
  	(println))


(defn open-slots [board]
  (filter number? board))


(defn slice-and-print [board]
	(print-board (subvec board 18 27))
	(print-board (subvec board 9 18))
	(print-board (subvec board 0 9)))


(defn valid-move? [board place]
 	(if (and (try (Integer. place) (catch Exception e ))  (and (>= (Integer. place) 0) (< (Integer. place) (count board))) )
        (if (= java.lang.Long (class (board (Integer. place)))) true) false))


(defn opposing-piece [piece]
 	(if (= piece "x") "o" "x"))


(defn place-piece [board piece place]
 	(assoc board (Integer. place) piece))


(defn take-turn [board piece]
  	(slice-and-print board)
  	(println (str piece " - Its your turn"))
  	(println "Select Slot (0 - 26) ")
  	(let [place (read-line) board board]
  		(if (valid-move? board place)
  	  	(place-piece board piece place)
  	  	(take-turn board piece))))


(defn winning-board? [board]
	(cond 
	  (some true? (map #(apply = %) (partition-all 3 board))) 1
	  (some true? (map #(apply = %) (take 3 (apply mapv list (partition 3 board))))) 1
	  (apply = (take-nth 4 board)) 1
	  (apply = (take 3 (take-nth 2 (drop 2 board)))) 1
	  :else 2))


(defn check-all-slices-for-win? [board]
		(cond
		;checks board 1 2 3
		(some #(= 1 %) (map #(winning-board? %) (map #(into [] %) (partition 9 board)))) 1
		;check board 4 5 6
		(some #(= 1 %) (map #(winning-board? %) (apply mapv vector (partition 3 board)))) 1
		;check board 7 8 9
		(some #(= 1 %) (map #(winning-board? %)  (map #(into [] %)(partition 9 (flatten (partition 3 (apply mapv vector (partition 9 board)))))))) 1
		;check board 10 
		(some #(= 1 %)(map #(winning-board? %) (vector (into [] (map #(get board %) [0 10 20 3 13 23 6 16 26]))))) 1
		;check board 11
		(some #(= 1 %)(map #(winning-board? %) (vector (into [] (map #(get board %) [18 10 2 21 13 5 24 16 8]))))) 1
		:else 2))


(defn compare-scores
  [results]
    (apply max-key :score (flatten results)))


(defn minimax [board symbol depth]
	; (slice-and-print board)
          (cond
          	;(if (= 1 (check-all-slices-for-win? board)){:score (+ -10 depth)} {:score 0} )
            ; (win? board) {:score (+ -10 depth)}
            ; (full? board) {:score 0}
            (= 1 (check-all-slices-for-win? board)) {:score (+ -10 depth)}
            (empty? (open-slots board) ) {:score 0}

            :else (compare-scores (for [x (open-slots board)]
                      (let [tempboard (assoc board x symbol)]
                      {:score (- (:score (minimax
                      tempboard
                      (opposing-piece symbol)
                      (inc depth)
                      )))
                      :index x})
                      ))))


(defn get-best-score [board symbol]
  (:index (minimax board symbol 0)))
  ; (describe "winnin-board")



(defn game-over? [board]
 	(if (or (= 1 (check-all-slices-for-win? board)) (not-any? number? board) ) true false))


(defn play-game [board sym]
  	(if (game-over? board) 
  		(do 
  	  	(if (= 1 (check-all-slices-for-win?  board) )	
  		(println (str "Great game! " (opposing-piece sym)))
  		(println "Its a TIE")))
		(play-game (take-turn board sym) (opposing-piece sym))))


(get-best-score board "x")

; (play-game board "x")







(defn -main
  [& args]
  (println "Hello World"))




