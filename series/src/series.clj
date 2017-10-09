(ns series)

(defn slices [string n]
  (if (> n (count string)) []
    (if (zero? n) [""]
      (loop [substring string result []]
        (if (> n (count substring))
          result
          (recur (subs substring 1)
            (conj result (subs substring 0 n)))
        )
      )
    )
  )
)
