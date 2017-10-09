(ns series)

(defn slices [string n]
  (if (> n (count string)) []
    (if (zero? n) [""]
      (loop [position 0 result []]
        (if (> (+ n position) (count string))
          result
          (recur (inc position)
           (conj result (subs string position (+ n position))))
        )
      )
    )
  )
)
