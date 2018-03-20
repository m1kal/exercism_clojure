(ns grains)

(defn square [n]
  (reduce (fn [acc iter] (* 2 acc)) 1/2 (range n))
)

(defn total []
 (- (square 65) 1)
)

