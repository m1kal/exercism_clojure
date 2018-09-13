(ns all-your-base)

(defn- number-from-base [digits base]
  (reduce #(+ (* base %1) %2) 0 digits))

(defn number-to-base [input base]
  (loop [output '() remainder input]
    (if
      (zero? remainder)
        (if (empty? output) '(0) output)
        (let [next-digit (mod remainder base)]
          (recur (cons next-digit output) (/ (- remainder next-digit) base))))))

(defn convert [input-base input-digits output-base]
  (cond
    (or (< input-base 2) (< output-base 2)) nil
    (not (every? #(< -1 % input-base) input-digits)) nil
    (empty? input-digits) '()
    :else
      (number-to-base (number-from-base input-digits input-base) output-base)))
