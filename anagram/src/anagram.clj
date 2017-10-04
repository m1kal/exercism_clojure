(ns anagram
  (use [clojure.string :only [split lower-case]])
)

(defn sort_lowercase [word]
  (sort (split (lower-case word) #""))
)

(defn compare [word]
  (fn [candidate]
    (and
      (apply = (map sort_lowercase [word candidate]))
      (apply not= (map lower-case [word candidate]))
    )
  )
)

(defn anagrams-for [word candidates]
  (filter (compare word) candidates )
)
