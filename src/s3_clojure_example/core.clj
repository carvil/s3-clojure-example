(ns s3-clojure-example.core
  (:require [clj-yaml.core :as yaml]
            [aws.sdk.s3 :as s3]))

(def s3-config-file
  "Location of the config file"
  "config/s3.yml")

;; (raw-s3-credentials)
;;   => "access-key: my-key\nsecret-key: my-secret\nbucket: my-bucket\n"
(defn raw-s3-credentials
  "A raw string containing the S3 credentials read from the yaml file"
  ([] (slurp s3-config-file))
  ([filepath] (slurp filepath)))

;; (s3-credentials)
;;   => {:access-key "my-key", :secret-key "my-secret", :bucket "my-bucket"}
(defn s3-credentials
  "A map of credentials read from the S3 yaml file"
  ([] (yaml/parse-string (raw-s3-credentials)))
  ([path] (yaml/parse-string (raw-s3-credentials path))))

;; (list-bucket)
;;   => {:bucket "map-tests", :objects ( ... )}
(defn list-bucket
  "Lists the contents of a bucket"
  []
  (let [creds (s3-credentials)
        bucket (get creds :bucket)]
    (s3/list-objects creds bucket)))

;; (slurp (download-object "map-tests" "test-data.csv"))
;;   => "id,nickname\n1,carvil\n2,someone\n3,a_guy\n"
;;  Read from the following file:
;;  id,nickname
;;  1,carvil
;;  2,someone
;;  3,a_guy
(defn download-object
  "Downloads a specific file from S3, given a bucket and a filename"
  [bucket filename]
  (:content (s3/get-object (s3-credentials) bucket filename)))
