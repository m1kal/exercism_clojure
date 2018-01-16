(ns binary-search)

(defn middle [input]
  (int (/ (dec (count input)) 2))
)

(defn search-for [element input]
  (loop [sublist (vec input) offset 0]
    (let [idx (middle sublist)]
      (cond
        (empty? sublist) (throw (Exception. "not found"))
        (= element (sublist idx)) (+ offset idx)
        (< element (sublist idx)) (recur (subvec sublist 0 idx) offset)
        :else (recur (subvec sublist (inc idx)) (+ offset (inc idx))))))
)
