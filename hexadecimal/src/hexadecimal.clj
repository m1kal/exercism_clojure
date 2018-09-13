(ns hexadecimal)

(defn- parse-character [input]
  (if
    (re-matches #"\d" input)
    (read-string input)
    (+ 10 (- (int (first input)) (int \a)))))


(defn- parse-string [input]
  (->>
    (clojure.string/split input #"")
    (map parse-character)
    (reduce #(+ (* 16 %1) %2) 0)))

(defn hex-to-int [input]
  (if
    (re-matches #"^[\da-f]*$" input)
    (parse-string input)
    0))
