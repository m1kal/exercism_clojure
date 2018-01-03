(ns flatten-array)

(defn flatten [input]
  (vec (filter #(not= nil %)
    (reduce (fn [flat elem]
      (if (vector? elem)
        (vec (concat flat (flatten elem)))
        (conj flat elem)
      )
    ) [] input)
  ))
)
