(ns difference-of-squares)

(def square #(* % %))
(def up-to (comp range inc))

(defn sum-of-squares [number]
  (->> number up-to (map square) (apply +)))

(defn square-of-sums [number]
  (->> number up-to (apply +) square))

(defn difference [number]
  (apply - (map #(% number) [square-of-sums sum-of-squares])))

