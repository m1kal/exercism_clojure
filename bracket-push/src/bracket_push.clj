(ns bracket-push)

(defn- match [stack bracket]
  (contains? #{[\( \)] [\[ \]] [\{ \}]} [(last stack) bracket]))

(defn- find-bracket [stack input-char]
  (cond
    (match stack input-char) (pop stack)
    (contains? #{\( \[ \{ \) \] \}} input-char) (conj stack input-char)
    :else stack))

(defn valid? [input]
  (empty? (reduce find-bracket [] input)))

