(ns collatz-conjecture)

(defn collatz [n]
  (if (< n 1) (throw (IllegalArgumentException. "Argument must be positive")))
  (loop [current n cnt 0]
    (if (= current 1)
      cnt
      (if (even? current)
        (recur (/ current 2) (inc cnt))
        (recur (inc (* current 3)) (inc cnt))
      )
    )
  )
)
