(ns scrabble-score)

(def scores {"q" 10 "z" 10 "j" 8 "x" 8 "k" 5 "f" 4 "h" 4 "v" 4 "w" 4 "y" 4 "b" 3 "c" 3 "m" 3 "p" 3 "d" 2 "g" 2})

(defn score-letter [letter]
  (scores (clojure.string/lower-case letter) 1)
  )

(defn score-word [word]
  (apply + (map score-letter word))
  )
