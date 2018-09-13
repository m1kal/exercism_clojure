(ns queen-attack)

(defn- eight-times [input separator]
  (clojure.string/join separator (repeat 8 input))
)

(defn- position-to-index [[y x]]
  (+ (* 2 x) (* 16 y)))

(defn add-piece [board piece color]
  (if piece
    (->
      board
      vec
      (assoc (position-to-index piece) color)
      (clojure.string/join))
    board))

(defn board-string [{:keys [w b]}]
  (->
    (str (-> "_" (eight-times " ") (eight-times "\n")) "\n")
    (add-piece w \W)
    (add-piece b \B)))

(defn- build-diagonals [[x y]]
  (apply concat
    (map
      (fn [idx] [[(+ idx x) (+ idx y)] [(+ idx x) (- y idx)]])
      (range -7 8))))

(defn- in-diagonal [w b]
  (some #{b} (build-diagonals w)))

(defn can-attack[{:keys [w b]}]
  (or
    (= (last w) (last b))
    (= (first w) (first b))
    (not (not (in-diagonal w b)))))
