(ns largest-series-product)

(defn largest-product [length input]
  (if-not
    (and (<= 0 length (count input)) (re-matches #"^\d*$" input))
    (throw Exception))
  (->>
    input
    (str "0")
    (#(clojure.string/split % #""))
    (map read-string)
    (partition length 1)
    (map #(apply * %))
    sort
    last))
