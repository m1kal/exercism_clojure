(ns prime-factors)

(defn of [number]
  (loop [out [] quotient number current 2]
    (if (= quotient 1)
      out
      (if (zero? (mod quotient current))
        (recur (conj out current) (/ quotient current) current)
        (recur out quotient (inc current))))))

