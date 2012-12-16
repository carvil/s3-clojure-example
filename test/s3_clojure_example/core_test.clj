(ns s3-clojure-example.core-test
  (:use clojure.test
        [s3-clojure-example.core :exclude [s3-config-file]]))

(def s3-config-file-example "config/s3.example.yml")

;; Tests for raw-s3-credentials-test
(deftest raw-s3-credentials-test
  (testing "Should read the example configurations"
    (is (= (raw-s3-credentials s3-config-file-example) "access-key: my-key\nsecret-key: my-secret\nbuket: my-bucket\n"))))

;; Tests for s3-credentials-test
(deftest s3-credentials-test
  (testing "Should return a map of configurations"
    (is (= (s3-credentials s3-config-file-example) {:access-key "my-key", :secret-key "my-secret", :buket "my-bucket"}))))
