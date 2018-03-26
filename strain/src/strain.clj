(ns strain)

(defn retain [predicate collection]
  (loop [out [] remains collection]
    (if (empty? remains)
      out
      (recur
        (if
          (predicate (first remains))
          (conj out (first remains))
          out)
        (rest remains)))))

(defn discard [predicate collection]
  (retain #(not (predicate %)) collection))

