(ns binary)

(defn to-decimal [binary]
  (int
    (apply +
      (map-indexed
        (fn [idx digit]
          (if (= digit \1)
          (Math/pow 2 idx)
          0))
        (reverse binary)))))
