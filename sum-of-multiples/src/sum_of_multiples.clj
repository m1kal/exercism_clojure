(ns sum-of-multiples)

(defn sum-of-multiples [numbers limit]
  (apply
    +
    (filter (fn [number] (some #(zero? (mod number %)) numbers)) (range 1 limit))))
