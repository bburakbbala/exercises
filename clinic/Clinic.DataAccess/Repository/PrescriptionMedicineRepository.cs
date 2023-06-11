using Clinic.DataAccess.Data;
using Clinic.DataAccess.Repository.IRepository;
using Clinic.Models;

namespace Clinic.DataAccess.Repository
{
    public class PrescriptionMedicineRepository : RepositoryAsync<PrescriptionMedicine>, IPrescriptionMedicineRepository
    {
        private readonly ApplicationDbContext _db;
        public PrescriptionMedicineRepository(ApplicationDbContext db) : base(db)
        {
            _db = db;
        }

        public void Update(PrescriptionMedicine prescriptionMedicine)
        {
        }
    }
}
