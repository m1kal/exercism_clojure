(ns raindrops)

(defn pling [number [key val]]
 (if (zero? (mod number key)) val)
)

(defn convert [number]
  (let [converted
        (clojure.string/join
          ""
          (reduce
            #(concat %1 (pling number %2))
            ""
            {3 "Pling" 5 "Plang" 7 "Plong"}))]
    (if
      (> (count converted) 0)
      converted
      (str number))))

