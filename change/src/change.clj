(ns change)

(defn- add-coin [coin-set coin amount]
  (mapv
    #(into coin-set (repeat % coin))
    (range (inc (/ (- amount (apply + coin-set)) coin)))))

(defn- add-coin-to-sets [coin-sets coin amount]
  (->>
    (apply concat (mapv #(add-coin % coin amount) coin-sets))
    (group-by #(apply + %))
    vals
    (map #(sort-by count %))
    (mapv first)))

(defn issue [amount coins]
  (cond
    (zero? amount)
    []
    (< amount (first (sort coins)))
    (throw (IllegalArgumentException. "cannot change" ))
    :else
    (loop [coin-sets [[0]] coins (reverse (sort coins))]
      (if
        (empty? coins)
        (->>
          coin-sets
          (filter #(= (apply + %) amount))
          (sort-by count)
          first
          rest
          reverse
        )
        (recur
          (add-coin-to-sets coin-sets (first coins) amount)
          (rest coins))))))

