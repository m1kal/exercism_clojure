(ns armstrong-numbers)

(defn digits [number]
  (map read-string (clojure.string/split (str number) #""))
)

(defn armstrong? [n]
  (let [digits (digits n) length (count digits) powers (map #(Math/pow % length) digits)]
    (= (int (apply + powers)) n)
  )
)
