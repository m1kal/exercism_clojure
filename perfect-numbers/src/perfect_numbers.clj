(ns perfect-numbers)


(defn classify [input]
  (if (< input 0) (throw (IllegalArgumentException. "Invalid argument")))
  (let [aliquot_sum (reduce #(if (= 0 (mod input %2)) (+ %1 %2) %1) 0 (range 1 input))]
    (cond
      (< aliquot_sum input) :deficient
      (> aliquot_sum input) :abundant
      :else :perfect))
)
