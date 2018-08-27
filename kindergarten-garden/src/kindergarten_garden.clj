(ns kindergarten-garden)

(def children ["alice" "bob" "charlie" "david" "eve" "fred" "ginny" "harriet" "ileana" "joseph" "kincaid" "larry"])

(def plants {\G :grass \C :clover \R :radishes \V :violets})

(defn garden
  ([string ] (garden string children))
  ([string students]
    (let [rows
          (->>
            (clojure.string/split string #"\n")
            (map #(partition 2 %))
            (apply interleave)
            (flatten)
            (partition 4))
          students
            (->>
              students
              (sort)
              (map clojure.string/lower-case)
              (mapv keyword))]
      (loop [output {} position 0]
        (if
          (= position (count rows))
          output
          (recur
            (assoc output (students position) (map plants (nth rows position)))
            (inc position)))))))

