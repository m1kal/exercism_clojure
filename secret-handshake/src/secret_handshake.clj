(ns secret-handshake)

(defn commands [number]
  (let [check-bit (partial bit-test number)]
    (->> (range 4)
         (filter check-bit)
         (mapv ["wink" "double blink" "close your eyes" "jump"])
         ((if (check-bit 4) reverse identity)))))

