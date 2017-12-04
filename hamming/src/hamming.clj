(ns hamming)

(defn distance [str1 str2]
  (if (= (count str1) (count str2))
    (count
      (filter (fn [x] (= false x))
        (map = 
          (clojure.string/split str1 #"")
          (clojure.string/split str2 #"")
        )
      )
    )
    nil
  )
)

