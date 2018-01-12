(ns isbn-verifier)

(defn isbn? [isbn]
  (let [parsed (clojure.string/replace isbn #"-" "")
        digits
          (vec
            (reverse
              (map
                #(if (= % "X") 10 (read-string %))
                (clojure.string/split parsed #""))))]
    (if (re-matches #"\d{9}[\dX]" parsed)
     (= 0 (mod (reduce #(+ %1 (* (digits %2) (inc %2))) 0 (range 10)) 11))
     false))
)
