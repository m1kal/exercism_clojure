(ns diamond)

(defn- mirror [input]
  (->> input
       reverse
       rest
       (concat input)))

(defn- empty-line [idx]
  (vec (repeat (inc idx) \ )))

(defn- letter [idx]
  (char (+ (int \A) idx)))

(defn- line [length idx]
  (assoc
    (empty-line length)
    (- length idx)
    (letter idx)))


(defn diamond [input]
  (let [char-idx (- (int input) (int \A))]
    (->>
      char-idx
      inc
      range
      (map #(line char-idx %))
      (map mirror)
      (map clojure.string/join)
      mirror)))

