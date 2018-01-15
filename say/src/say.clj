(ns say)

(def digits ["" "one" "two" "three" "four" "five" "six" "seven" "eight" "nine"])
(def teens ["ten" "eleven" "twelve" "thirteen" nil "fifteen" nil "eighteen"])
(def tens [nil nil "twenty" "thirty" "forty" "fifty" nil nil "eighty"])
(def units ["" "thousand" "million" "billion"])

(defn get-teens [input]
  (let [shifted (- input 10) special_case (teens shifted)]
    (or special_case (str (digits shifted) "teen")))
)

(defn get-tens [input]
  (let [shifted (int (/ input 10)) special_case (tens shifted)]
    (or special_case (str (digits shifted) "ty"))  )
)

(defn format-digit [pre digit post]
  (if (> digit 0) (str pre (digits digit) post))
)

(defn get-digit [input position]
  (read-string (str (get (clojure.string/join (reverse (str input))) position \0)))
)

(defn three-digits [input]
  (let [two_digits (mod input 100)
       below_ten (and (< two_digits 10) (digits (get-digit input 0)))
       below_twenty (and (< two_digits 20) (> two_digits 9) (get-teens two_digits))
       num_tens (get-tens two_digits)
       hundreds (format-digit "" (get-digit input 2) " hundred ")
      ]
    (str
      hundreds
      (or
        below_ten
        below_twenty
        (str num_tens (format-digit "-" (get-digit input 0) "")))))
)


(defn number [input]
  (if
    (or (< input 0) (> input 999999999999))
    (throw (IllegalArgumentException. "Out of range")))
  (if (= input 0) "zero"
    (clojure.string/trim
      (clojure.string/join " "
        (reverse
          (map-indexed
            #(let [num (three-digits (read-string %2))] (if (> (count num) 2) (str num " " (units %1)) ""))
            (map
              #(clojure.string/join (reverse %))
              (partition-all 3 (reverse (str input)))))))))
)
