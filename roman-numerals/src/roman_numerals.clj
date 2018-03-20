(ns roman-numerals)

(def digits {1000 "M" 900 "CM" 500 "D" 400 "CD" 100 "C"
            90 "XC" 50 "L" 40 "XL" 10 "X" 9 "IX" 5 "V" 4 "IV" 1 "I"})

(defn numerals [decimal]
  (loop [roman "" remainder decimal]
  (if (zero? remainder)
    roman
    (let [next-digit (apply max (filter #(>= remainder %) (keys digits)))]
    (recur (clojure.string/join "" [roman (digits next-digit)]) (- remainder next-digit))
  ))))
