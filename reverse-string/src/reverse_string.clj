(ns reverse-string)

(defn reverse-string [input]
  (loop [reversed "" original input]
    (if (empty? original)
      reversed
      (recur (str (first original) reversed) (rest original))
    )
  )
)
