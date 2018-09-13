(ns accumulate)

(defn accumulate [function input]
  (loop [output [] remainder input]
    (if
      (empty? remainder)
      output
      (recur (conj output (function (first remainder))) (rest remainder)))

  
  )
      )
