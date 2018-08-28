(ns difference-of-squares)

(defn- square [number]
  (* number number))

(defn sum-of-squares [number]
  (->>
    number
    inc
    range
    (map square)
    (apply +)))

(defn square-of-sums [number]
  (->>
    number
    inc
    range
    (apply +)
    square))

(defn difference [number]
 (apply - (map #(% number) [square-of-sums sum-of-squares]))
)

