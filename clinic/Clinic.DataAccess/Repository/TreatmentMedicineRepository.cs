using Clinic.DataAccess.Data;
using Clinic.DataAccess.Repository.IRepository;
using Clinic.Models;

namespace Clinic.DataAccess.Repository
{
    public class TreatmentMedicineRepository : RepositoryAsync<TreatmentMedicine>, ITreatmentMedicineRepository
    {
        private readonly ApplicationDbContext _db;
        public TreatmentMedicineRepository(ApplicationDbContext db) : base(db)
        {
            _db = db;
        }

        public void Update(TreatmentMedicine treatmentMedicine)
        {
        }
    }
}
