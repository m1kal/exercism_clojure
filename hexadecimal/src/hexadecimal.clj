(ns hexadecimal)

(defn- parse-character [input]
  (if
    (re-matches #"\d" input)
    (read-string input)
    (+ 10 (- (int (first input)) (int \a)))))

(defn hex-to-int [input]
  (if
    (re-matches #"^[\da-f]*$" input)
    (->>
      input
      (#(clojure.string/split % #""))
      (map parse-character)
      (reduce #(+ (* 16 %1) %2) 0))
    0))
