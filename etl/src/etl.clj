(ns etl)

(defn transform [input]
  (reduce
    (fn [output-map input-map]
      (conj output-map
        (reduce
          (fn [partial-map val]
            (conj partial-map {(clojure.string/lower-case val) (first input-map)})
          ) {} (last input-map)
        )
      )
    ) {} input
  )
)
