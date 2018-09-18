(ns nth-prime)

(defn- multiple-of? [small]
  (fn [big]
    (and (> big small) (zero? (mod big small)))))

(defn- remove-multiples [numbers idx]
  (remove (multiple-of? (nth numbers idx)) numbers))

(defn- sieve [number]
  (reduce remove-multiples (range 2 (inc number)) (range (Math/sqrt number))))

(defn- pnt-limit [n]
  (* n (+ (Math/log n) (Math/log (Math/log n) ))))

(defn nth-prime [n]
  (if (< n 1) (throw (IllegalArgumentException.)))
  (->> n (+ 2) pnt-limit sieve (drop (dec n)) first))

