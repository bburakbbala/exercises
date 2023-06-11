using Clinic.Models;

namespace Clinic.DataAccess.Repository.IRepository
{
    public interface IPrescriptionMedicineRepository : IRepositoryAsync<PrescriptionMedicine>
    {
        void Update(PrescriptionMedicine prescriptionMedicine);
    }
}