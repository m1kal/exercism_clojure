(ns beer-song
  (use [clojure.string :only [capitalize]])
)

(defn subst_number [n]
  (cond
    (> n 0) n
    (= n 0) "no more"
    :else 99
  )
)

(defn bottles_of_beer [n]
  (str (subst_number n) " bottle" (if (= n 1) "" "s") " of beer")
)

(defn bottles_on_the_wall [n]
  (str (bottles_of_beer n) " on the wall")
)

(defn first_line [n]
  (capitalize (str (bottles_on_the_wall n) ", " (bottles_of_beer n) ".\n"))
)

(defn action [n]
  (if (> n 0)
    (str "Take " (if (= n 1) "it" "one") " down and pass it around, ")
    "Go to the store and buy some more, ")
)

(defn verse [n]
  (str (first_line n) (action n) (bottles_on_the_wall (dec n)) ".\n")
)

(defn sing
  ([num] (sing num 0))
  ([start end]
    (str (verse start) ( if (> start end) ( str "\n" (sing (dec start) end))))
  )
)
