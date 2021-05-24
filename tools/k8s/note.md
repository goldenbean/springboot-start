# Notes

```bash


kubectl get pods -n gateway | grep livy-server | awk '{print $1}'  | xargs kubectl -n gateway logs -f

kubectl exec -it busybox  -n default -- ping 10.244.69.155

kubectl exec -it livy-k8s-rscdriver-1621846388126-driver -n default -- netstat -anpt

kubectl port-forward  livy-k8s-rscdriver-1621846388126-driver --address 10.58.10.198 10000:10000

```